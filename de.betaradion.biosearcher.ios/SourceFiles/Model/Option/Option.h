//
//  Option.h
//  bioSearcher
//
//  Created by Yannick Wiesner on 29.08.14.
//  Copyright (c) 2014 Betaradion. All rights reserved.
//

#import <Foundation/Foundation.h>

@interface Option : NSObject

@property (readonly) int oid;
@property (readonly) NSString *name;
@property (readonly) NSString *description;
@property (readonly) NSString *img;

@end
