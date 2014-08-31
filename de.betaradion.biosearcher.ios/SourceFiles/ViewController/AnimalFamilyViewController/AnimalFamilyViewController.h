//
//  AnimalFamilyViewController.h
//  TabbedApp
//
//  Created by Betaradion on 12.02.13.
//  Copyright (c) 2013 Betaradion. All rights reserved.
//

#import <UIKit/UIKit.h>
#import "BSTableViewController.h"
#import "Family.h"

@interface AnimalFamilyViewController : BSTableViewController
@property (nonatomic, strong) NSDictionary* selectedFamily;
@property (nonatomic, strong) NSArray* families;
- (void)refreshAction;

@end
