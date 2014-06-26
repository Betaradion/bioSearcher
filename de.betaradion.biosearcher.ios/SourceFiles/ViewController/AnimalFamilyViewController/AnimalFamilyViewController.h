//
//  AnimalFamilyViewController.h
//  TabbedApp
//
//  Created by Betaradion on 12.02.13.
//  Copyright (c) 2013 Betaradion. All rights reserved.
//

#import <UIKit/UIKit.h>
#import "BSTableViewController.h"

@interface AnimalFamilyViewController : BSTableViewController
@property (nonatomic, strong) NSDictionary* selectedFamily;
@property (nonatomic, strong) NSDictionary* families;
- (void)refreshAction;

@end
