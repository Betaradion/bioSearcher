//
//  JSONConnection.h
//  bioSearcher
//
//  Created by Martin on 26.06.14.
//  Copyright (c) 2014 Betaradion. All rights reserved.
//

#import "Connection.h"

#define webPath @"http://localhost:8080/rest_service/rest/"
#define familiesPath @"families/"
#define charactersPath @"characters/"
#define optionsPath @"options/"
#define search @"search/"

@interface JSONConnection : Connection

-(void)loadData:(DataType)type forParentId:(NSNumber *)parentid;
-(void)searchForSpeciesWithFamilyID:(NSNumber *)id andCharacters:(NSDictionary *)characters;

@end
