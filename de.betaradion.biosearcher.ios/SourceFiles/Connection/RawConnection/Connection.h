//
//  Connection.h
//  TabbedApp
//
//  Created by Betaradion on 22.11.12.
//  Copyright (c) 2012 Betaradion. All rights reserved.
//

#import <Foundation/Foundation.h>

@interface Connection : NSObject <NSURLConnectionDataDelegate>

@property (nonatomic,retain)NSMutableData *receivedData;
@property (nonatomic, copy) NSString* field;


-(void)networkActivity:(BOOL)activity;

@end
