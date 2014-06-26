//
//  AnimalFamilyViewController.m
//  TabbedApp
//
//  Created by Betaradion on 12.02.13.
//  Copyright (c) 2013 Betaradion. All rights reserved.
//

#import "AnimalFamilyViewController.h"
#import "pListConnection.h"
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
        
        pListConnection *conn = [[pListConnection alloc] init];
        [conn loadFamilysFromServer:@"families"];
    }

}

- (void)refreshAction
{
    [self showHud:YES];
    pListConnection *conn = [[pListConnection alloc] init];
    [conn loadFamilysFromServer:@"families"];
    self.families = [NSDictionary dictionary];
}


- (void) didFinishLoadingFromNet:(NSNotification*)notification
{
    NSDictionary* info = notification.userInfo;

    NSString* field = info[@"loadedField"];
    if ([field isEqualToString:@"families"])
    {
        self.families = info[@"families"];
        [self.refreshControl endRefreshing];
        [self hideHud:YES];
        [self.tableView reloadData];
        

    }
}

- (NSInteger)numberOfSectionsInTableView:(UITableView *)tableView
{
    // Return the number of sections.
    return 1;
}

- (NSInteger)tableView:(UITableView *)tableView numberOfRowsInSection:(NSInteger)section
{
    // Return the number of rows in the section.
    return [self.families count];
}

- (UITableViewCell *)tableView:(UITableView *)tableView cellForRowAtIndexPath:(NSIndexPath *)indexPath
{
    static NSString *CellIdentifier = @"MyCell";
    UITableViewCell *cell = [tableView dequeueReusableCellWithIdentifier:CellIdentifier forIndexPath:indexPath];
    
    [cell setSelectedBackgroundView:[self createSelectedBackgroundView:cell.frame]];
    cell.textLabel.font = cellFont;
    
    NSString *row = [NSString stringWithFormat:@"%i",indexPath.row + 1];
    //Reihe plus 1 setzten weil nullbasierend
    
    NSDictionary *currentFamily = self.families[row];
    

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
    NSString *row = [NSString stringWithFormat:@"%i",indexPath.row + 1];
    self.selectedFamily = [self.families objectForKey:row];
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
