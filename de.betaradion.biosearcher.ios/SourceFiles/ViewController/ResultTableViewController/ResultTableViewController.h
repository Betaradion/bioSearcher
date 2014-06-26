//
//  ResultTableViewController.h
//  TabbedApp
//
//  Created by Betaradion on 15.12.12.
//  Copyright (c) 2012 Betaradion. All rights reserved.
//

#import <UIKit/UIKit.h>
#import "BSTableViewController.h"

@interface ResultTableViewController : BSTableViewController <UISearchBarDelegate>

@property (weak, nonatomic) IBOutlet UISearchBar *SearchBar;
@property (nonatomic, strong) NSDictionary* species;
@property (nonatomic, strong) NSMutableDictionary* options;
@property (nonatomic, strong) NSDictionary* family;
@property (nonatomic, strong) NSMutableDictionary* images;

@end
