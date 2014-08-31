//
//  FamilyController.h
//  bioSearcher
//
//  Created by Yannick Wiesner on 29.08.14.
//  Copyright (c) 2014 Betaradion. All rights reserved.
//

#import <Foundation/Foundation.h>
#import "LRRestyClientResponseDelegate.h"
#import "LRRestyResponse.h"

@interface LoadingController : NSObject <LRRestyClientResponseDelegate>

#define FamiliesResourcePath @"families"

@property NSArray *families;

+ (LoadingController *)sharedManager;
- (void)restClient:(LRRestyClient *)client receivedResponse:(LRRestyResponse *)response;
- (void)load;

@end
