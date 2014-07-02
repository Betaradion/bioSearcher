//
//  ProfileTableViewController.m
//
//  Created by Betaradion on 16.01.13.
//  Copyright (c) 2013 Betaradion. All rights reserved.
//

#import "ProfileTableViewController.h"
#import "JSONConnection.h"
#import "imageConnection.h"
#import "PopoverView.h"
#import "Flurry.h"

@interface ProfileTableViewController ()

@property (nonatomic, strong) UIImage* profileImage;
@end

@implementation ProfileTableViewController{
    UITapGestureRecognizer *tapImage;
    UIScrollView *scrollView;
}

- (void)viewDidLoad
{
    [super viewDidLoad];
    self.profile = [[NSDictionary alloc] init];
    tapImage = [[UITapGestureRecognizer alloc] initWithTarget:self action:@selector(imageTaped:)];
    tapImage.numberOfTouchesRequired = 1;
    tapImage.numberOfTapsRequired = 1;
    
    [Flurry logEvent:@"Profile" withParameters:@{self.species[@"name"]: @"Name of Species"}];
}

- (void)viewWillAppear:(BOOL)animated
{
    [super viewWillAppear:animated];
    if(self.profile.count == 0){
        [self showHud:animated];
        
        JSONConnection *conn = [[JSONConnection alloc] init];
        [conn loadData:DataTypeProfile forParentId:self.species[@"SID"]];
        
        
    }
    self.title = self.species[@"name"];
    
    if(self.profileImage == nil && self.species[@"pic"] != nil){
        imageConnection* imgConn = [[imageConnection alloc] init];
        [imgConn loadPictureFromServer:self.species miniature:NO];
    }
}

- (void)refreshAction
{
    [self showHud:YES];
    JSONConnection *conn = [[JSONConnection alloc] init];
    [conn loadData:DataTypeProfile forParentId:self.species[@"SID"]];
    
}


- (void) didFinishLoadingFromNet:(NSNotification*)notification
{
    NSDictionary* info = notification.userInfo;
    
    NSString* field = info[@"loadedField"];
    if ([field isEqualToString:@"profile"])
    {
        [[[imageConnection alloc] init] networkActivity:YES];
        self.profile = info[@"profile"];
        [self.refreshControl endRefreshing];
        [self hideHud:YES];
        [self.tableView reloadData];
    }
    
    if([field isEqualToString:@"image"]){
        self.profileImage = info[@"image"];
        NSMutableArray* rowArray = [[NSMutableArray alloc]init];
        [rowArray addObject: [NSIndexPath indexPathForRow:0 inSection:0] ];
        [self.tableView  reloadRowsAtIndexPaths:rowArray withRowAnimation:YES];
    }
}

- (NSInteger)numberOfSectionsInTableView:(UITableView *)tableView
{
    // Return the number of sections.
    return 1;
}

- (NSInteger)tableView:(UITableView *)tableView numberOfRowsInSection:(NSInteger)section
{
    return [self.profile count] + 2;
}

- (UITableViewCell *)tableView:(UITableView *)tableView cellForRowAtIndexPath:(NSIndexPath *)indexPath
{
    UITableViewCell *cell;
    if (indexPath.row == 0) {
        NSString *CellIdentifier = @"PictureCell";
        cell = [tableView dequeueReusableCellWithIdentifier:CellIdentifier forIndexPath:indexPath];
        
        UIImageView *imageView = (UIImageView *)[cell viewWithTag:1];

        if(self.profileImage == nil){
            UIActivityIndicatorView* loadingProfileImage = [[UIActivityIndicatorView alloc] init];
            loadingProfileImage.color = DEFAULT_TINT_COLOR;
            CGRect frame = loadingProfileImage.frame;
            frame.origin.y = 73.0f;
            
            frame.origin.x = [UIScreen mainScreen].bounds.size.width / 2;
            [loadingProfileImage startAnimating];
            loadingProfileImage.frame = frame;
            [imageView addSubview:loadingProfileImage];
            cell.userInteractionEnabled = NO; 
        } else {
            cell.userInteractionEnabled = YES;   
            imageView.image = self.profileImage;
            imageView.userInteractionEnabled = YES;
            [imageView addGestureRecognizer:tapImage];
            for (UIView* subview in imageView.subviews) {
                [subview removeFromSuperview];
            }
        }
        
        return cell;
    }else if(indexPath.row == self.profile.count){
        UITableViewCell *cell = [tableView dequeueReusableCellWithIdentifier:@"MyCell" forIndexPath:indexPath];
        
        [cell setSelectedBackgroundView:[self createSelectedBackgroundView:cell.frame]];
        cell.textLabel.font = cellFont;
                
        NSDictionary *currentProfileRow = self.profile[@"1"];
        
        NSString *userId = [NSString stringWithFormat:@"%@ %@", currentProfileRow[@"firstname"],currentProfileRow[@"lastname"]];
        
        cell.accessoryType = UITableViewCellAccessoryNone;
        cell.textLabel.text = userId;
        cell.detailTextLabel.text = @"Copyright";
        cell.userInteractionEnabled = NO;
       
        return cell;
    } else {
        UITableViewCell *cell = [tableView dequeueReusableCellWithIdentifier:@"MyCell" forIndexPath:indexPath];
        
        [cell setSelectedBackgroundView:[self createSelectedBackgroundView:cell.frame]];
        cell.textLabel.font = cellFont;
        
        NSDictionary *currentProfileRow = self.profile[[NSString stringWithFormat:@"%i", indexPath.row]];
        
        //Aus der aktuellen Reihe den String "vorname" auslesen und dem Tabellenlabes gleichsetzen.
        cell.textLabel.text = currentProfileRow[@"name"];
        cell.detailTextLabel.text = currentProfileRow[@"value"];
        cell.userInteractionEnabled = YES;
        
         return cell;
    }
}

- (CGFloat)tableView:(UITableView *)tableView heightForRowAtIndexPath:(NSIndexPath *)indexPath
{
   if (indexPath.row == 0) {
     return 146.0f; 
    } else {
        return 44.0f;
    }
}

- (void)tableView:(UITableView *)tableView didSelectRowAtIndexPath:(NSIndexPath *)indexPath
{
    if (indexPath.row != 0)
    {
        UITableViewCell *cell = [tableView cellForRowAtIndexPath:indexPath];
        
//        CGRect frame = CGRectMake(0.0, 0.0, cell.frame.size.width, cell.frame.size.height);
//        frame.origin = [cell convertPoint:cell.frame.origin toView:self.tableView];
        //CGRect cellRect = CGRectMake(0.0, 0.0, cell.frame.size.width, cell.frame.size.height);
        [PopoverView showPopoverAtPoint:cell.detailTextLabel.frame.origin inView:cell withText:cell.detailTextLabel.text delegate:nil];
        
    }
    
    [tableView deselectRowAtIndexPath:indexPath animated:YES];

}

- (void)imageTaped:(UIGestureRecognizer *)gestureRecognizer{
    [Flurry logEvent:@"Image Fullscreen"];
    
   UIImageView *imageView = (UIImageView *)[self.view viewWithTag:1];
    imageView = [[UIImageView alloc] initWithImage:self.profileImage];
    imageView.frame = self.view.frame;
    imageView.backgroundColor = [UIColor blackColor];
    [imageView sizeToFit];
    
    scrollView = [[UIScrollView alloc] initWithFrame:imageView.frame];
    scrollView.minimumZoomScale = 0.5;
    scrollView.maximumZoomScale = 2.5;
    scrollView.zoomScale = 1.0f;
    scrollView.contentSize = self.profileImage.size;
    
    [self.view addSubview:scrollView];
    [scrollView addSubview:imageView];
    
    // Tell the scroll view the size of the contents
    
    
    [UIView beginAnimations:nil context:nil];
    [UIView setAnimationDuration:0.5f];
    
    CGRect screenSize = [UIScreen mainScreen].bounds;
    scrollView.bounds = screenSize;
    
    [UIView commitAnimations];
}

@end
