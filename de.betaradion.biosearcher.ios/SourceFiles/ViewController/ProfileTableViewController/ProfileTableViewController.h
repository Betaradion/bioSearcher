//
//  ProfileTableViewController.h
//  TabbedApp
//
//  Created by Betaradion on 16.01.13.
//  Copyright (c) 2013 Betaradion. All rights reserved.
//

#import <UIKit/UIKit.h>
#import "BSTableViewController.h"

@interface ProfileTableViewController : BSTableViewController

@property (nonatomic, retain) NSDictionary* species;
@property (nonatomic, retain) NSDictionary* profile;

@end
