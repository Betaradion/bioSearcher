//
//  pListConnection.h
//  TabbedApp
//
//  Created by Betaradion on 14.05.13.
//  Copyright (c) 2013 Betaradion. All rights reserved.
//

#import "Connection.h"

#define webPath @"http://biosearcher.sytes.net/sql/index.php"

@interface pListConnection : Connection

-(void)loadFamilysFromServer:(NSString *)type;
-(void)loadFromServer:(NSString *)type parentId:(NSString*)parentId;
-(void)loadSpeciesFromServer:(NSString *)type options:(NSMutableDictionary*)options family:(NSDictionary*)family;


@end
