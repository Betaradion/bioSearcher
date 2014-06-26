//
//  BSTableViewController.h
//  TabbedApp
//
//  Created by Betaradion on 07.05.13.
//  Copyright (c) 2013 Betaradion. All rights reserved.
//  Done: - Fixed Image Bug
//        - deselect Selection added to all classes.
//        - Popups at profile Table are added.
//        - Loading Image Selecting deactivated
//        - Bug fixed: Loading No Image stopped
//        - Fixed Seague Bug. didSelectAtIndexPath is not used any more.

#import <UIKit/UIKit.h>
#import "MBProgressHUD.h"

@interface BSTableViewController : UITableViewController

- (void)showHud:(BOOL)animated;
- (void)hideHud:(BOOL)animated;
- (UIView *)createSelectedBackgroundView:(CGRect)frame;

@end
