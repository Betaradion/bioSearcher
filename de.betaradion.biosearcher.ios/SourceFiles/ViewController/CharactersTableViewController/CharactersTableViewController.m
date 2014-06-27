//
//  CharactersTableViewController.m
//  TabbedApp
//
//  Created by Betaradion on 13.02.13.
//  Copyright (c) 2013 Betaradion. All rights reserved.
//

#import "CharactersTableViewController.h"
#import "ResultTableViewController.h"
#import "MBProgressHUD.h"
#import "JSONConnection.h"
#import "ActionSheetStringPicker.h"
#import "PopoverView.h"

@interface CharactersTableViewController ()
@end

@implementation CharactersTableViewController

- (void)viewDidLoad
{
    [super viewDidLoad];
    self.selectedOptions = [[NSMutableDictionary alloc] init];
    self.characters = [[NSArray alloc] init];
    self.options = [[NSArray alloc] init];
}

- (void)viewWillAppear:(BOOL)animated
{
    [super viewWillAppear:animated];
    if(self.characters.count == 0){
        [self showHud:animated];
        JSONConnection* conn = [[JSONConnection alloc] init];
        [conn loadData:DataTypeCharacters forParentId:self.family[@"id"]];
    }
    self.title = self.family[@"name"];
    
}

- (void)refreshAction
{
    [self showHud:YES];
    JSONConnection *conn = [[JSONConnection alloc] init];
    [conn loadData:DataTypeCharacters forParentId:self.family[@"FID"]];
    
    // weil durch das refreshen potenziell Einträge aus der
    // Liste der Charakteristika verschwinden bzw sich verschieben
    // können - oder neue dazu - passen danach die Inidzes der
    // bislang gewwählten Options nicht mehr. Einfachste Lösung hier:
    // Gewählte Options löschen.
    self.selectedOptions = [NSMutableDictionary dictionary];
    
    self.characters = [NSArray array];
}


- (void) didFinishLoadingFromNet:(NSNotification*)notification
{
    NSDictionary* info = notification.userInfo;
    NSString *type = info[@"loadedField"];
    if ([type isEqualToString:[NSString stringWithFormat:@"%d", DataTypeCharacters]])
    {
        self.characters = info[@"data"];
        [self.tableView reloadData];
        [self hideHud:YES];
        [self.refreshControl endRefreshing];
    }
    else if ([type isEqualToString:[NSString stringWithFormat:@"%d", DataTypeOptions]])
    {
        self.options = info[@"data"];
        [self hideHud:YES];
        [self showPicker];
    }
}

- (void) showPicker
{
    NSMutableArray* optionsArray = [[NSMutableArray alloc] init];
    [optionsArray addObject:NSLocalizedString(@"options.picker.no.selection", @"Key for PickerView: noSelection")];
    
    for (int i=0; i<self.options.count; i++)
    {
        NSString *optionName = self.options[i][@"name"];
        [optionsArray addObject:optionName];
    }
    
    NSDictionary* previousSelectedOption = self.selectedOptions[self.selectedCharacter];
    
    
    NSInteger initalSelection = 0;
    if (previousSelectedOption != nil)
    {
        initalSelection = [optionsArray indexOfObject:previousSelectedOption[@"name"]];
    }
    
    
    [ActionSheetStringPicker showPickerWithTitle:self.selectedCharacter[@"name"]
                                            rows:optionsArray
                                initialSelection:initalSelection
                                          target:self
                                   successAction:@selector(didSelectOption:origin:)
                                    cancelAction:@selector(actionPickerCancelled:)
                                          origin:self.tabBarController.tabBar];
}

- (void)didSelectOption:(NSNumber*)selectedOption origin:(id)origin
{
    if (selectedOption.integerValue == 0){
        [self.selectedOptions removeObjectForKey:self.selectedCharacter];
    } else {
        self.selectedOptions[self.selectedCharacter] = self.options[selectedOption.intValue-1];
    }
    
    [self.tableView reloadData];
}


- (void)actionPickerCancelled:(id)origin
{
    NSLog(@"Cancel: %@", origin);
}

- (NSInteger)numberOfSectionsInTableView:(UITableView *)tableView
{
    return 1;
}

- (NSInteger)tableView:(UITableView *)tableView numberOfRowsInSection:(NSInteger)section
{
    return [self.characters count];
}

- (UITableViewCell *)tableView:(UITableView *)tableView cellForRowAtIndexPath:(NSIndexPath *)indexPath
{
    // Zelle definieren
    static NSString *CellIdentifier = @"MyCell";
    UITableViewCell *cell = [tableView dequeueReusableCellWithIdentifier:CellIdentifier forIndexPath:indexPath];
    
    [cell setSelectedBackgroundView:[self createSelectedBackgroundView:cell.frame]];
    
    NSDictionary* currentCharacter = self.characters[indexPath.row];
    
    cell.textLabel.text = currentCharacter[@"name"];
    cell.detailTextLabel.text = self.selectedOptions[currentCharacter][@"name"];
    cell.textLabel.font = cellFont;
    
    //    TODO: Siehe infoButtonTabbed (tapped)
    if (currentCharacter[@"description"]){
        UIButton* infoButton = [UIButton buttonWithType:UIButtonTypeInfoDark];
        [infoButton addTarget:self action:@selector(infoButtonTabbed:) forControlEvents:UIControlEventTouchUpInside];
        cell.accessoryView = infoButton;
        infoButton.tag = indexPath.row;
        
    }
    
    return cell;
}

#pragma mark - Table view delegate

-(void)tableView:(UITableView *)tableView didSelectRowAtIndexPath:(NSIndexPath *)indexPath
{

    self.selectedCharacter = self.characters[indexPath.row];
    [self showHud:YES];
    
    JSONConnection* conn = [[JSONConnection alloc] init];
    [conn loadData:DataTypeOptions forParentId:self.selectedCharacter[@"id"]];

    
    [tableView deselectRowAtIndexPath:indexPath animated:YES];
}

-(void)prepareForSegue:(UIStoryboardSegue *)segue sender:(id)sender
{
    if ([segue.identifier isEqualToString:@"ShowResultList"])
    {
        ResultTableViewController* resultTVC = segue.destinationViewController;
        resultTVC.options = self.selectedOptions;
        resultTVC.family = self.family;
    }
}

-(void)infoButtonTabbed:(UIButton *)button
{
    NSDictionary* currentCharacter = self.characters[button.tag + 1];
    [PopoverView showPopoverAtPoint:button.center inView:button.superview withText:currentCharacter[@"description"] delegate:nil];
}
@end