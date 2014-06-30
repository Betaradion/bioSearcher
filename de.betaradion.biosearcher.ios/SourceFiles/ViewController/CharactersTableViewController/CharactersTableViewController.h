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
@property (nonatomic, strong) NSArray* characters;//All possible characters to choose from
@property (nonatomic, strong) NSArray* options;//All matching options to choose from

@property (nonatomic, strong) NSMutableDictionary* selectedOptions;//All chosen options <CID,OID>
@property (nonatomic, strong) NSDictionary* selectedCharacter;//Chosen Character <id,(...),
                                                              //                  name,(...)>

-(void)infoButtonTabbed:(UIButton *)button;

@end
