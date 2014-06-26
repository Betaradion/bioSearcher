//
//  InfoTableViewController.m
//  bioSearcher
//
//  Created by Betaradion on 18.08.13.
//  Copyright (c) 2013 Betaradion. All rights reserved.
//

#import "InfoTableViewController.h"
#import "WebViewController.h"
#import "Flurry.h"

@interface InfoTableViewController ()

@end

@implementation InfoTableViewController

- (id)initWithStyle:(UITableViewStyle)style
{
    self = [super initWithStyle:style];
    if (self) {
        // Custom initialization
    }
    return self;
}

- (void)viewDidLoad
{
    [super viewDidLoad];

    // Uncomment the following line to preserve selection between presentations.
    // self.clearsSelectionOnViewWillAppear = NO;
 
    // Uncomment the following line to display an Edit button in the navigation bar for this view controller.
    // self.navigationItem.rightBarButtonItem = self.editButtonItem;
}

- (void)didReceiveMemoryWarning
{
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
}


#pragma mark - Table view delegate

- (void)tableView:(UITableView *)tableView didSelectRowAtIndexPath:(NSIndexPath *)indexPath
{
    // Navigation logic may go here. Create and push another view controller.
    /*
     <#DetailViewController#> *detailViewController = [[<#DetailViewController#> alloc] initWithNibName:@"<#Nib name#>" bundle:nil];
     // ...
     // Pass the selected object to the new view controller.
     [self.navigationController pushViewController:detailViewController animated:YES];
     */
    
    [Flurry logEvent:@"Cell_Pushed"];
}

- (void)prepareForSegue:(UIStoryboardSegue *)segue sender:(id)sender
{
   NSIndexPath *indexPath = [self.tableView indexPathForCell:sender];
//    NSString *row = [NSString stringWithFormat:@"%i",indexPath.row + 1];
//    self.selectedFamily = [self.families objectForKey:row];
   [self.tableView deselectRowAtIndexPath:indexPath animated:YES];
    
    if ([segue.identifier isEqualToString:@"ShowHelpView"])
    {
        WebViewController* webViewTVC = segue.destinationViewController;
        
        webViewTVC.webViewValue = @"ShowHelpView";
    }
    
    if ([segue.identifier isEqualToString:@"ShowContactView"])
    {
        WebViewController* webViewTVC = segue.destinationViewController;
        
        webViewTVC.webViewValue = @"ShowContactView";
    }
    
    if ([segue.identifier isEqualToString:@"ShowMemberView"])
    {
        WebViewController* webViewTVC = segue.destinationViewController;
        
        webViewTVC.webViewValue = @"ShowMemberView";
    }
}

@end
