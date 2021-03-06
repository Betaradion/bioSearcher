//
//  AnimalFamilyViewController.m
//  TabbedApp
//
//  Created by Betaradion on 12.02.13.
//  Copyright (c) 2013 Betaradion. All rights reserved.
//

#import "AnimalFamilyViewController.h"
#import "CharactersTableViewController.h"
#import "ResultTableViewController.h"
#import "Flurry.h"
#import "LoadingController.h"

@interface AnimalFamilyViewController ()

@end

@implementation AnimalFamilyViewController

- (void)viewDidLoad
{
    [super viewDidLoad];
    self.selectedFamily = [[NSDictionary alloc] init];
    self.families = [[NSArray alloc] init];
    
    [[LoadingController sharedManager] addObserver:self
                                        forKeyPath:@"families"
                                           options:0
                                           context:nil];
}

- (void)viewWillAppear:(BOOL)animated
{
    [super viewWillAppear:animated];
    if(self.families.count == 0){
        [self showHud:animated];
        }
}

- (void)refreshAction
{
    [self showHud:YES];
    self.families = [NSArray array];
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
    
    Family *currentFamily = self.families[indexPath.row];

    //Aus der aktuellen Reihe den String "vorname" auslesen und dem Tabellenlabes gleichsetzen.
    cell.textLabel.text = currentFamily.name;
    
    return cell;
}

- (void)tableView:(UITableView *)tableView didDeselectRowAtIndexPath:(NSIndexPath *)indexPath
{
    [tableView deselectRowAtIndexPath:indexPath animated:YES];
}

- (void)prepareForSegue:(UIStoryboardSegue *)segue sender:(id)sender
{
    NSIndexPath *indexPath = [self.tableView indexPathForCell:sender];
    self.selectedFamily = self.families[indexPath.row];

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

- (void)observeValueForKeyPath:(NSString *)keyPath ofObject:(id)object change:(NSDictionary *)change context:(void *)context
{
    if ([keyPath isEqualToString:@"families"])
    {
        self.families = [LoadingController sharedManager].families;
        [self.refreshControl endRefreshing];
        [self hideHud:YES];
        [self.tableView reloadData];

    }
}


@end
