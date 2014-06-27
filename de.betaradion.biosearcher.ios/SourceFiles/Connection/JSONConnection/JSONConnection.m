//
//  JSONConnection.m
//  bioSearcher
//
//  Created by Martin on 26.06.14.
//  Copyright (c) 2014 Betaradion. All rights reserved.
//

#import "JSONConnection.h"

@implementation JSONConnection

-(void)connect:(NSString *)sqlParameter forDatafield:(NSString*)field
{
    self.field = field;
    NSURLRequest *request = [self buildPListRequest:sqlParameter];
    NSURLConnection *connection = [[NSURLConnection alloc] initWithRequest:request delegate:self];
    [self networkActivity:YES];
    [connection start];
}

-(void)loadFamiliesFromServer:(NSString *)type
{
    NSString* httpRequest = [[NSString alloc] init];
    if ([type isEqual: @"families"]) {
        httpRequest = [NSString stringWithFormat:@"%@ %@",  webPath ,"families/"];
        [self connect:httpRequest forDatafield:@"families"];
    }
}

-(NSMutableURLRequest*)buildPListRequest:(NSString *)sqlParameter
{
    NSData *postData = [sqlParameter dataUsingEncoding:NSASCIIStringEncoding allowLossyConversion:NO];
    NSString *postLength = [NSString stringWithFormat:@"%d", [postData length]];
    
    
    NSMutableURLRequest *request = [[NSMutableURLRequest alloc] init];
    [request setURL: [NSURL URLWithString:webPath]];
    [request setHTTPMethod:@"POST"];
    [request setValue:postLength forHTTPHeaderField:@"Content-Length"];
    [request setValue:@"application/x-www-form-urlencoded" forHTTPHeaderField:@"Content-Type"];
    [request setHTTPBody:postData];
    
    return request;
}

@end
