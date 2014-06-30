//
//  ResultTableViewController.m
//  TabbedApp
//
//  Created by Betaradion on 15.12.12.
//  Copyright (c) 2012 Betaradion. All rights reserved.
//

#import "ResultTableViewController.h"
#import "JSONConnection.h"
//#import "imageConnection.h"
#import "ProfileTableViewController.h"

@interface ResultTableViewController ()

@property (nonatomic,retain) NSDictionary* selectedSpecies;

@end

@implementation ResultTableViewController

- (void)viewDidLoad
{
    [super viewDidLoad];
    self.selectedSpecies = [[NSDictionary alloc] init];
    self.images = [[NSMutableDictionary alloc] init];
}

- (void)viewWillAppear:(BOOL)animated
{
    [super viewWillAppear:animated];
    if(self.species == nil)
    {
        [self showHud:animated];
        
        JSONConnection *conn = [[JSONConnection alloc] init];
        [conn searchForSpeciesWithFamilyID:self.family[@"id"] andCharacters:self.options];
    }
}

- (void)refreshAction
{
    [self showHud:YES];
    JSONConnection *conn = [[JSONConnection alloc] init];
    [conn searchForSpeciesWithFamilyID:self.family[@"id"] andCharacters:self.options];
    self.images = [NSMutableDictionary dictionary];
    self.species = [NSDictionary dictionary];
}


- (void) didFinishLoadingFromNet:(NSNotification*)notification
{
    NSDictionary* info = notification.userInfo;
    
    NSString* type = info[@"loadedField"] ;
    if ([type isEqualToString:[NSString stringWithFormat:@"%d", DataTypeSpecies]])
    {
        self.species = info[@"data"];
        [self.refreshControl endRefreshing];
        [self hideHud:YES];
        [self.tableView reloadData];
        
//        for (NSDictionary* currentSpecies in [self.species allValues])
//        {
//            if ([currentSpecies objectForKey:@"pic"] != nil)
//            {
//                imageConnection* conn = [[imageConnection alloc] init];
//                [conn loadPictureFromServer:currentSpecies miniature:YES];
//            }
//        }
//    }
//    if ([type isEqualToString:[NSString stringWithFormat:@"%d", DataTypeImages]])
//    {
//        [self.images setObject:info[@"image"] forKey:info[@"species"]];
//        
//        [self.tableView reloadData];
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
    if ([self.species count] == 0 ){
        return 1;
    } else {
        return [self.species count];
    }
}

- (UITableViewCell *)tableView:(UITableView *)tableView cellForRowAtIndexPath:(NSIndexPath *)indexPath
{
    static NSString *CellIdentifier = @"MyCell";
    UITableViewCell *cell = [tableView dequeueReusableCellWithIdentifier:CellIdentifier forIndexPath:indexPath];
    
    [cell setSelectedBackgroundView:[self createSelectedBackgroundView:cell.frame]];
    cell.textLabel.font = cellFont;
    
    if ([self.species count]== 0){
        cell.textLabel.text = NSLocalizedString(@"result.tableviewcontroller.cell.no.results", @"Error when displaying: No Species found.");
        return cell;
    } else {
        NSString *row =[NSString stringWithFormat:@"%i",indexPath.row + 1];
        //Reihe plus 1 setzten weil nullbasierend
        NSDictionary* currentSpecies = self.species[row];
        
        cell.textLabel.text = currentSpecies[@"name"];
        
        if (self.images[currentSpecies]) {
            cell.imageView.image = self.images[currentSpecies];
        }
        
        return cell;
    }
    
}

- (void)tableView:(UITableView *)tableView didDeselectRowAtIndexPath:(NSIndexPath *)indexPath
{
    [tableView deselectRowAtIndexPath:indexPath animated:YES];
}

- (void)tableView:(UITableView *)tableView didHighlightRowAtIndexPath:(NSIndexPath *)indexPath
{
    NSString *row = [NSString stringWithFormat:@"%i",indexPath.row + 1];
    self.selectedSpecies = self.species[row];
}

- (void)prepareForSegue:(UIStoryboardSegue *)segue sender:(id)sender
{
    if ([segue.identifier isEqualToString:@"showSpeciesProfile"])
    {
        ProfileTableViewController* profileTVC = segue.destinationViewController;
//        profileTVC.species = self.selectedSpecies;
    }
    
}

@end

