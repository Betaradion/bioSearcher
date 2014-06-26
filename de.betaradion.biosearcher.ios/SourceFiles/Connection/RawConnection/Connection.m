//
//  Connection.m
//  TabbedApp
//
//  Created by Betaradion on 22.11.12.
//  Copyright (c) 2012 Betaradion. All rights reserved.
//

#import "Connection.h"

@implementation Connection

#pragma mark -
#pragma mark Main Functions

-(id)init
{
    self = [super init];
    if (self)
    {
        self.receivedData = [[NSMutableData alloc] init];
    }
    return self;
}

#pragma mark -
#pragma mark Delegate
-(void)connection:(NSURLConnection *)connection didReceiveData:(NSData *)data
{
    // NSLog([[NSString alloc] initWithData:data encoding:NSISOLatin1StringEncoding]);
    [self.receivedData appendData:data];
}

-(void)connection:(NSURLConnection *)connection didFailWithError:(NSError *)error
{
    if(error != NULL)
    {
        NSLog(@"%@ %d %@", [error domain], [error code], [error localizedDescription]);
        [[NSNotificationCenter defaultCenter] postNotificationName:@"DidFailLoadingFromNetworkNotification"
                                                            object:self
                                                          userInfo:@{ @"loadedField" : self.field, @"error" : error }];
        [self networkActivity:NO];
    }
}

#pragma mark -
#pragma mark Help Functions

-(void)networkActivity:(BOOL)activity
{
    if (activity)
    {
        [UIApplication sharedApplication].networkActivityIndicatorVisible = YES;
    }
    else
    {
        [UIApplication sharedApplication].networkActivityIndicatorVisible = NO;
    }
}

@end
