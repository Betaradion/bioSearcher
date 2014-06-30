//
//  imageConnection.h
//  TabbedApp
//
//  Created by Betaradion on 14.05.13.
//  Copyright (c) 2013 Betaradion. All rights reserved.
//

#import "Connection.h"

#define imagePath @"http://biosearcher.sytes.net/pic/"

typedef enum DataType {
    DataTypeImages
} DataType;

@interface imageConnection : Connection

-(void)loadPictureFromServer:(NSDictionary *)species miniature:(BOOL *)miniature;

@end
