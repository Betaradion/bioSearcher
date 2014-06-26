//
//  OverwiewViewController.m
//  TabbedApp
//
//  Created by Betaradion on 14.02.13.
//  Copyright (c) 2013 Betaradion. All rights reserved.
//

#import "OverwiewViewController.h"
#import "Connection.h"
#import "File.h"
#import <QuartzCore/QuartzCore.h>

@interface OverwiewViewController ()

@end

@implementation OverwiewViewController

- (id)initWithNibName:(NSString *)nibNameOrNil bundle:(NSBundle *)nibBundleOrNil
{
    self = [super initWithNibName:nibNameOrNil bundle:nibBundleOrNil];
    if (self) {
        // Custom initialization
    }
    return self;
}

- (void)viewWillAppear:(BOOL)animated
{
    for(UIView *subview in[self.view subviews])
    {
        if (subview.backgroundColor == [UIColor blackColor]) {
            [subview removeFromSuperview];
        }
        
    }

}

- (void)viewDidLoad
{
    [super viewDidLoad];
    
    self.data = [MySingleton sharedMySingleton];
    
	// Do any additional setup after loading the view.
}

- (void)didReceiveMemoryWarning
{
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
}


- (NSInteger)numberOfSectionsInTableView:(UITableView *)tableView
{
    // Return the number of sections.
    return 1;
}

- (NSInteger)tableView:(UITableView *)tableView numberOfRowsInSection:(NSInteger)section
{
    // Return the number of rows in the section.
    [UIApplication sharedApplication].networkActivityIndicatorVisible = YES;
    
    float x = self.view.frame.size.width * 110/640;
    float y = self.view.frame.size.height * 230/830;
    float width = self.view.frame.size.width - 2 * x;
    float height = self.view.frame.size.height - 2 * y;
    
    CGRect mainframe = CGRectMake(x, y, width, height);
    UIView *loadView = [[UIView alloc] initWithFrame:mainframe];
    loadView.backgroundColor = [UIColor blackColor];
    loadView.layer.cornerRadius = 20;
    loadView.alpha = 0.8f;
    
    x = (loadView.frame.size.width - 65) / 2;
    y = loadView.frame.size.height * 55/155 * 1/3;
    width = 65;
    height = 65;
    
    CGRect activityframe = CGRectMake(x, y, width, height);
    
    UIActivityIndicatorView *activity = [[UIActivityIndicatorView alloc] initWithFrame:activityframe];
    [activity startAnimating];
    
    x = 0;
    y = loadView.frame.size.height * 2/3;
    width = loadView.frame.size.width;
    height = 20;
    
    CGRect titleframe = CGRectMake(x, y, width, height);
    
    UILabel *title = [[UILabel alloc] initWithFrame:titleframe];
    title.text = @"Loading Data";
    title.textColor = [UIColor whiteColor];
    title.backgroundColor = [UIColor clearColor];
    title.textAlignment = NSTextAlignmentCenter;
    title.font = [UIFont boldSystemFontOfSize:16.0f];
    
    
    [loadView addSubview:activity];
    [loadView addSubview:title];
    [self.view addSubview:loadView];
    [self.view bringSubviewToFront:loadView];

    NSString *sql = @"sql=select_*_from_families_order_by_sort_asc";
    
    NSData *postData = [sql dataUsingEncoding:NSASCIIStringEncoding allowLossyConversion:YES];
    
    NSString *postLength = [NSString stringWithFormat:@"%d", [postData length]];
    
    
    NSMutableURLRequest *request = [[NSMutableURLRequest alloc] init];
//    [request setURL: [NSURL URLWithString:webPath]];
    [request setHTTPMethod:@"POST"];
    [request setValue:postLength forHTTPHeaderField:@"Content-Length"];
    [request setValue:@"application/x-www-form-urlencoded" forHTTPHeaderField:@"Content-Type"];
    [request setHTTPBody:postData];
    
    NSError *error = nil;
    NSData *result = [NSURLConnection sendSynchronousRequest:request returningResponse:nil error:&error];
    if (error) {
        UIAlertView *alert = [[UIAlertView alloc]initWithTitle:@"No Internet Connection" message:@"Please connect to the Internet and retry" delegate:nil cancelButtonTitle:@"OK" otherButtonTitles: nil];
        [alert show];
        
        [self.tabBarController setSelectedIndex:0];
        NSLog(@"%@", error);
    }
    
    File *fileProcessing = [[File alloc] init];
    [fileProcessing writeFile:@"temp.txt" withContent:result];
    // Zur Fileausgabe
    //NSData *datas = [fileProcessing readFile:@"temp.txt"];
    //NSLog(@"%@", [[NSString alloc] initWithData:datas encoding:NSISOLatin1StringEncoding]);
    
    self.data.families = [fileProcessing readIntoDictionary:@"temp.txt"];
    //[fileProcessing outputDictionary:self.data.families];
    
    [UIApplication sharedApplication].networkActivityIndicatorVisible = NO;
    [loadView removeFromSuperview];
    
    return [self.data.families count];
}

- (UITableViewCell *)tableView:(UITableView *)tableView cellForRowAtIndexPath:(NSIndexPath *)indexPath
{
    static NSString *CellIdentifier = @"MyCell";
    UITableViewCell *cell = [tableView dequeueReusableCellWithIdentifier:CellIdentifier forIndexPath:indexPath];
    
    NSMutableDictionary *dict = self.data.families;
    NSString *row;
    //Reihe plus 1 setzten weil nullbasierend
    row = [NSString stringWithFormat:@"%i",indexPath.row + 1];
    //Aus der aktuellen Reihe den String "vorname" auslesen und dem Tabellenlabes gleichsetzen.
    cell.textLabel.text =[[dict objectForKey:row] objectForKey:@"name"];
    //cell.detailTextLabel.text = [[dict objectForKey:row] objectForKey:@"value"];
    
    return cell;
}

- (void)tableView:(UITableView *)tableView didHighlightRowAtIndexPath:(NSIndexPath *)indexPath
{
    NSString *row;
    row = [NSString stringWithFormat:@"%i",indexPath.row + 1];
    self.data.selectedFamily = [[self.data.families objectForKey:row] objectForKey:@"FID"];
    self.data.characters = nil;
    
}

@end
