//
//  CharactersTableViewController.h
//  TabbedApp
//
//  Created by Betaradion on 13.02.13.
//  Copyright (c) 2013 Betaradion. All rights reserved.
//

#import <UIKit/UIKit.h>
#import "BSTableViewController.h"

@interface CharactersTableViewController : BSTableViewController

@property (nonatomic, copy) NSDictionary* family;

@property (weak, nonatomic) IBOutlet UIBarButtonItem *find;
//- (IBAction)prepareFind:(id)sender;
@property (nonatomic, strong) NSDictionary* characters;
@property (nonatomic, strong) NSDictionary* options;

@property (nonatomic, strong) NSMutableDictionary* selectedOptions;
@property (nonatomic, strong) NSDictionary* selectedCharacter;

-(void)infoButtonTabbed:(UIButton *)button;

@end
