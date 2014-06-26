//
//  BSTableViewController.m
//  TabbedApp
//
//  Created by Betaradion on 07.05.13.
//  Copyright (c) 2013 Betaradion. All rights reserved.
//

#import "BSTableViewController.h"

@interface BSTableViewController ()

@property (nonatomic, strong) MBProgressHUD* hud;

@end

@implementation BSTableViewController

- (void)viewDidLoad
{
    [super viewDidLoad];
    
    self.hud = [[MBProgressHUD alloc] initWithView:self.view];
    self.hud.labelText = NSLocalizedString(@"bstableviewcontroller.message.loading", @"Display message while loading.");
    self.hud.mode = MBProgressHUDModeIndeterminate;
    [self.navigationController.view addSubview:self.hud];
    
    [self.refreshControl addTarget:self
                            action:@selector(refreshAction)
                  forControlEvents:UIControlEventValueChanged];
}

- (void)viewWillAppear:(BOOL)animated
{
    [[NSNotificationCenter defaultCenter] addObserver:self
                                             selector:@selector(didFinishLoadingFromNet:)
                                                 name:@"DidFinishLoadingFromNetworkNotification"
                                               object:nil];
    [[NSNotificationCenter defaultCenter] addObserver:self
                                             selector:@selector(didFailLoadingFromNet:)
                                                 name:@"DidFailLoadingFromNetworkNotification"
                                               object:nil];
    
    
}

- (void)viewWillDisappear:(BOOL)animated
{
    [self hideHud:animated];
    [[NSNotificationCenter defaultCenter] removeObserver:self];
    [super viewWillDisappear:animated];
}


- (void)showHud:(BOOL)animated{
    [self.hud show:animated];
}

- (void)hideHud:(BOOL)animated{
    [self.hud hide:animated];
}

- (void)didFailLoadingFromNet:(NSNotification*)notification
{
    if ([notification.name isEqualToString:@"DidFailLoadingFromNetworkNotification"])
    {
        NSError* error = notification.userInfo[@"error"];
        [self hideHud:YES];
        // alert view: fehlermeldung aus error.localizedDescription
        UIAlertView *alert = [[UIAlertView alloc]initWithTitle:NSLocalizedString(@"bstableviewcontroller.error.connection", @"Connection did fail with Error - BSTableViewController") message:error.localizedDescription delegate:nil cancelButtonTitle:@"OK" otherButtonTitles: nil];
        [alert show];
        
        [self.tabBarController setSelectedIndex:0];
        NSLog(@"%@", error);
    }
}

- (UIView *)createSelectedBackgroundView:(CGRect)frame
{
    UIView* selectionColorView = [[UIView alloc] initWithFrame:frame];
    selectionColorView.backgroundColor = CUSTOM_CELL_SELECTED_BACKGROUND_COLOR;
    return selectionColorView;
}



@end
