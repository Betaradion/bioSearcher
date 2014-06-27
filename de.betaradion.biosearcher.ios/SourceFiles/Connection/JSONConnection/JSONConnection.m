//
//  JSONConnection.m
//  bioSearcher
//
//  Created by Martin on 26.06.14.
//  Copyright (c) 2014 Betaradion. All rights reserved.
//

#import "JSONConnection.h"

@implementation JSONConnection

-(void)connect:(NSString *)jsonParameter forDatafield:(NSString*)field
{
    NSURLRequest *request = [NSURLRequest requestWithURL:[NSURL URLWithString:[NSString stringWithFormat:@"%@%@", webPath, field]]];
    
    NSURLConnection *conn = [[NSURLConnection alloc] initWithRequest:request delegate:self];
    [conn start];
}

-(void)connectionDidFinishLoading:(NSURLConnection*)connection
{
    NSError *error = nil;
    id object = [NSJSONSerialization
                 JSONObjectWithData:self.receivedData
                 options:0
                 error:&error];
    
    if ([NSJSONSerialization isValidJSONObject:object])
    {
        NSLog(@"Completed!");
        
        //        [[NSNotificationCenter defaultCenter] postNotificationName:@"DidFailLoadingFromNetworkNotification"
        //                                                            object:self
        //                                                          userInfo:@{ @"loadedField" : self.field}];
        
        
        if([object isKindOfClass:[NSArray class]])
        {
            NSDictionary *results = object;
           
            NSLog(@"%@", results);
            
            [[NSNotificationCenter defaultCenter] postNotificationName:@"DidFinishLoadingFromNetworkNotification"
                                                                object:self
                                                              userInfo:@{ @"loadedField" : @"families", @"JSONArray" : results}];
        }
    } else {
        NSLog(@"Error");
    }
    
    [self networkActivity:NO];
    
   }

-(void)loadFamiliesFromServer:(NSString *)type
{
    
}

-(void)loadFromServer:(NSString *)type parentId:(NSString*)parentId
{
    
}

-(void)loadSpeciesFromServer:(NSString *)type options:(NSMutableDictionary*)options family:(NSDictionary*)family
{
    
}


@end
