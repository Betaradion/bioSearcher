    //
//  WebViewController.m
//  bioSearcher
//
//  Created by Betaradion on 18.08.13.
//  Copyright (c) 2013 Betaradion. All rights reserved.
//

#import "WebViewController.h"
#import "Flurry.h"

@interface WebViewController ()
@property (weak, nonatomic) IBOutlet UIWebView *infotextWebView;

@end

@implementation WebViewController

- (id)initWithNibName:(NSString *)nibNameOrNil bundle:(NSBundle *)nibBundleOrNil
{
    self = [super initWithNibName:nibNameOrNil bundle:nibBundleOrNil];
    if (self) {
        // Custom initialization
    }
    return self;
}

- (void)viewDidLoad
{
    [super viewDidLoad];
    // load html file from the main bundle, language will
    // be determined automatically
    NSString *resourcePath;
    
    // entscheiden, welche Html-Datei geladen werden soll
    if ([self.webViewValue isEqualToString:@"ShowHelpView"])
    {
        resourcePath = @"intro";
    } else if ([self.webViewValue isEqualToString:@"ShowContactView"]) {
        resourcePath = @"aboutText";
    } else if ([self.webViewValue isEqualToString:@"ShowMemberView"]) {
        resourcePath = @"member";
    }
    
     [Flurry logEvent:self.webViewValue];
    
    
    NSString *htmlFile = [[NSBundle mainBundle] pathForResource:resourcePath ofType:@"html"
                                                    inDirectory:@"/"];
    NSData *htmlData = [NSData dataWithContentsOfFile:htmlFile];
    [self.infotextWebView loadData:htmlData MIMEType:@"text/html"
                  textEncodingName:@"UTF-8"
                           baseURL:[NSURL URLWithString:@""]];
}

- (void)didReceiveMemoryWarning
{
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
}



@end
