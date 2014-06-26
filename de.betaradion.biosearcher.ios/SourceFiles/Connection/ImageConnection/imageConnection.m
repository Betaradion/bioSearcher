//
//  imageConnection.m
//  TabbedApp
//
//  Created by Betaradion on 14.05.13.
//  Copyright (c) 2013 Betaradion. All rights reserved.
//

#import "imageConnection.h"

@interface imageConnection()

@property (nonatomic, strong) NSDictionary* species;
@property (nonatomic) BOOL* miniature;

-(void)connect;
-(NSMutableURLRequest*)buildImageRequest;
-(void)connectionDidFinishLoading:(NSURLConnection*) connection;


@end

@implementation imageConnection
-(void)connect
{
    NSURLRequest *request = [self buildImageRequest];
    NSURLConnection *connection = [[NSURLConnection alloc] initWithRequest:request delegate:self];
    [self networkActivity:YES];
    [connection start];
}

-(void)loadPictureFromServer:(NSDictionary *)species miniature:(BOOL *)miniature
{
    self.species = species;
    self.miniature = miniature;
    
    [self connect];
}

-(NSMutableURLRequest*)buildImageRequest
{
    NSMutableURLRequest *request = [[NSMutableURLRequest alloc] init];
    NSString* urlString = [NSString stringWithFormat:@"%@%@", imagePath, self.species[@"pic"]];
    
    if(self.miniature)
    {
        urlString = [urlString stringByAppendingString:@"-small.png"];
    }
    
    [request setURL: [NSURL URLWithString:urlString]];
    
    return request;
}

-(void)connectionDidFinishLoading:(NSURLConnection*)connection
{
    [self networkActivity:NO];
    UIImage* image = [[UIImage alloc] initWithData:self.receivedData];
    if (image != nil)
    {
        [[NSNotificationCenter defaultCenter] postNotificationName:@"DidFinishLoadingFromNetworkNotification"
    object:self
    userInfo:@{ @"loadedField" : @"image", @"species" : self.species, @"image" : image}];
    }
}
@end
