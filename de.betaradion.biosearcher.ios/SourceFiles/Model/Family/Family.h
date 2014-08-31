//
//  Family.h
//  bioSearcher
//
//  Created by Yannick Wiesner on 29.08.14.
//  Copyright (c) 2014 Betaradion. All rights reserved.
//

#import <Foundation/Foundation.h>
#import "JSONModel.h"
#import "Character.h"

@interface Family : JSONModel

@property  int fid;
@property  NSString *name;
@property  NSString<Optional> *description;
@property  NSString<Optional> *img;
@property  Character<Optional> *character;

@end
