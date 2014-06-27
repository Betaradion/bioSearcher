//
//  AnimalFamilyViewController.m
//  TabbedApp
//
//  Created by Betaradion on 12.02.13.
//  Copyright (c) 2013 Betaradion. All rights reserved.
//

#import "AnimalFamilyViewController.h"
#import "JSONConnection.h"
#import "CharactersTableViewController.h"
#import "ResultTableViewController.h"
#import "Flurry.h"

@interface AnimalFamilyViewController ()

@end

@implementation AnimalFamilyViewController

- (void)viewDidLoad
{
    [super viewDidLoad];
    self.selectedFamily = [[NSDictionary alloc] init];
    self.families = [[NSDictionary alloc] init];

}

- (void)viewWillAppear:(BOOL)animated
{
    [super viewWillAppear:animated];
    if(self.families.count == 0){
        [self showHud:animated];
        
        JSONConnection *conn = [[JSONConnection alloc] init];
        [conn connect:@"" forDatafield:@"families"];
    }

}

- (void)refreshAction
{
    [self showHud:YES];
    JSONConnection *conn = [[JSONConnection alloc] init];
    [conn loadFamiliesFromServer:@"families"];
    self.families = [NSArray array];
}


- (void) didFinishLoadingFromNet:(NSNotification*)notification
{
    NSDictionary* info = notification.userInfo;

    NSString* field = info[@"loadedField"];
    if ([field isEqualToString:@"families"])
    {
        self.families = info[@"JSONArray"];
        [self.refreshControl endRefreshing];
        [self hideHud:YES];
        [self.tableView reloadData];
    }
}

- (NSInteger)numberOfSectionsInTableView:(UITableView *)tableView
{
    return 1;
}

- (NSInteger)tableView:(UITableView *)tableView numberOfRowsInSection:(NSInteger)section
{
    return [self.families count];
}

- (UITableViewCell *)tableView:(UITableView *)tableView cellForRowAtIndexPath:(NSIndexPath *)indexPath
{
    static NSString *CellIdentifier = @"MyCell";
    UITableViewCell *cell = [tableView dequeueReusableCellWithIdentifier:CellIdentifier forIndexPath:indexPath];
    
    [cell setSelectedBackgroundView:[self createSelectedBackgroundView:cell.frame]];
    cell.textLabel.font = cellFont;
    
    NSDictionary *currentFamily = self.families[indexPath.row];

    //Aus der aktuellen Reihe den String "vorname" auslesen und dem Tabellenlabes gleichsetzen.
    cell.textLabel.text = currentFamily[@"name"];
    
    return cell;
}

- (void)tableView:(UITableView *)tableView didDeselectRowAtIndexPath:(NSIndexPath *)indexPath
{
    [tableView deselectRowAtIndexPath:indexPath animated:YES];
}

- (void)prepareForSegue:(UIStoryboardSegue *)segue sender:(id)sender
{
    NSIndexPath *indexPath = [self.tableView indexPathForCell:sender];
    self.selectedFamily = [self.families objectAtIndex:indexPath.row];

    [self.tableView deselectRowAtIndexPath:indexPath animated:YES];
    
    if ([segue.identifier isEqualToString:@"ShowCharacteristic"])
    {
        CharactersTableViewController* charactersTVC = segue.destinationViewController;
        charactersTVC.family = self.selectedFamily;
        
         [Flurry logEvent:@"Family Selected" withParameters:@{self.selectedFamily: @"Family Selected", @"Search": @"called by"}];
    }

    if ([segue.identifier isEqualToString:@"ShowResultList"])
    {
        ResultTableViewController* resultTVC = segue.destinationViewController;
        resultTVC.family = self.selectedFamily;
        resultTVC.options = [NSMutableDictionary dictionary];
        
        [Flurry logEvent:@"Family Selected" withParameters:@{self.selectedFamily: @"Family Selected", @"Overview": @"called by"}];
    }
}


@end
