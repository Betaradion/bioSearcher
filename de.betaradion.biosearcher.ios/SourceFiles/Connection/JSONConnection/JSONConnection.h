//
//  JSONConnection.h
//  bioSearcher
//
//  Created by Martin on 26.06.14.
//  Copyright (c) 2014 Betaradion. All rights reserved.
//

#import "Connection.h"

#define webPath @"http://biosearcher.sytes.net/rest/"

@interface JSONConnection : Connection

-(void)loadFamiliesFromServer:(NSString *)type;
-(void)loadFromServer:(NSString *)type parentId:(NSString*)parentId;
-(void)loadSpeciesFromServer:(NSString *)type options:(NSMutableDictionary*)options family:(NSDictionary*)family;
-(void)connect:(NSString *)sqlParameter forDatafield:(NSString*)field;
-(NSString *)buildSearchSqlFromOptions:(NSMutableDictionary*)options family:(NSDictionary*)family;
-(NSMutableURLRequest*)buildPListRequest:(NSString *)sqlParameter;

@end
