//
//  FamilyController.m
//  bioSearcher
//
//  Created by Yannick Wiesner on 29.08.14.
//  Copyright (c) 2014 Betaradion. All rights reserved.
//

#import "LoadingController.h"
#import "LRResty.h"
#import "Family.h"

@implementation LoadingController

+ (LoadingController *)sharedManager {
    static LoadingController *sharedFamilyController = nil;
    @synchronized(self) {
        if (sharedFamilyController == nil)
            sharedFamilyController = [[self alloc] init];
    }
    return sharedFamilyController;
}

- (id)init {
    //Do loading stuff....
    [self load];
    return self;
}

- (void)load {
    [[LRResty client] get:[WebServicePath stringByAppendingString:FamiliesResourcePath] delegate:self];
}

- (void)restClient:(LRRestyClient *)client receivedResponse:(LRRestyResponse *)response {
    // unnesesairy but required of RestyDelegate
    }

- (void)restClient:(LRRestyClient *)client request:(LRRestyRequest *)request receivedData:(NSData *)data {
    NSLog([[NSString alloc] initWithData:data encoding:NSUTF8StringEncoding]);
    NSError *err = nil;
    NSArray *tempFamilies = [Family arrayOfModelsFromData:data error:&err];
    if (err) {
        NSLog(@"%@",err);
    } else {
        self.families = tempFamilies;
    }

}

@end
