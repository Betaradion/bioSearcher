//
//  pListConnection.m
//  TabbedApp
//
//  Created by Betaradion on 14.05.13.
//  Copyright (c) 2013 Betaradion. All rights reserved.
//

#import "pListConnection.h"
@interface pListConnection()

-(void)connect:(NSString *)sqlParameter forDatafield:(NSString*)field;
-(NSString *)buildSearchSqlFromOptions:(NSMutableDictionary*)options family:(NSDictionary*)family;
-(NSMutableURLRequest*)buildPListRequest:(NSString *)sqlParameter;

@end

@implementation pListConnection

-(void)connect:(NSString *)sqlParameter forDatafield:(NSString*)field
{
    self.field = field;
    NSURLRequest *request = [self buildPListRequest:sqlParameter];
    NSURLConnection *connection = [[NSURLConnection alloc] initWithRequest:request delegate:self];
    [self networkActivity:YES];
    [connection start];
}

-(void)loadFamilysFromServer:(NSString *)type
{
    NSString* sql = [[NSString alloc] init];
    if ([type isEqual: @"families"]) {
        sql = @"sql=select_*_from_families_order_by_sort_asc";
        [self connect:sql forDatafield:@"families"];
    }
}

- (NSString *)buildSearchSqlFromOptions:(NSMutableDictionary*)options family:(NSDictionary*)family
{
    NSString *sql = [NSString stringWithFormat:@"sql=select_name,_SID,_pic_from_species_where_FID_=_%@",family[@"FID"]];
    sql = [sql stringByAppendingString:@"_AND_SID_in_(Select_SID_from_main_where_SID_>_0"];
    for (NSDictionary* character in [options allKeys]) {
        if ((options[character] != 0) &! ([options[character] isEqual:@""])) {
            NSString* innerSql = [[NSString alloc] init];
            innerSql = [NSString stringWithFormat:@"_And_SID_in_(Select_SID_from_main_where_CID_=_%@", character[@"CID"]];
            sql = [sql stringByAppendingString:innerSql];
            innerSql = [NSString stringWithFormat:@"_And_OID_=_%@)", options[character][@"OID"]];
            sql = [sql stringByAppendingString:innerSql];
            
        }
    }
    sql = [sql stringByAppendingString:@")_order_by_name_asc"];
    return sql;
}

-(void)loadFromServer:(NSString *)type parentId:(NSString*)parentId
{
    NSString* sql = [[NSString alloc] init];
    if ([type isEqual: @"characters"]) {
        sql = [NSString stringWithFormat:@"sql=select_*_from_characters_where_FID_=_%@_order_by_name_asc", parentId];
        [self connect:sql forDatafield:@"characters"];
    }
    
    if ([type isEqual:@"options"]) {
        sql = [NSString stringWithFormat:@"sql=select_*_from_options_where_CID_=_%@_order_by_name_asc", parentId];
        [self connect:sql forDatafield:@"options"];
    }
    
    if ([type isEqual:@"profile"]) {
        sql = [NSString stringWithFormat:@"sql=SELECT_distinct_p.CID,_c.name,_p.value_,_u.firstname_,_u.lastname_from_characters_c,_profile_p_,_users u_,_species_s_where_c.CID_=_p.CID_and_p.SID_=_s.SID_and_s.UserID_=_u.UserID_and_p.SID_=_%@_order_by_c.sort_asc", parentId];
        [self connect:sql forDatafield:@"profile"];
    }
}

-(void)loadSpeciesFromServer:(NSString *)type options:(NSMutableDictionary*)options family:(NSDictionary*)family
{
    NSString* sql = [[NSString alloc] init];
    if ([type isEqual:@"fittingSpecies"]) {
        sql = [self buildSearchSqlFromOptions:options family:family];
        [self connect:sql forDatafield:@"fittingSpecies"];
    }
    
}

-(NSMutableURLRequest*)buildPListRequest:(NSString *)sqlParameter
{
    NSData *postData = [sqlParameter dataUsingEncoding:NSASCIIStringEncoding allowLossyConversion:NO];
    NSString *postLength = [NSString stringWithFormat:@"%d", [postData length]];
    
    
    NSMutableURLRequest *request = [[NSMutableURLRequest alloc] init];
    [request setURL: [NSURL URLWithString:webPath]];
    [request setHTTPMethod:@"POST"];
    [request setValue:postLength forHTTPHeaderField:@"Content-Length"];
    [request setValue:@"application/x-www-form-urlencoded" forHTTPHeaderField:@"Content-Type"];
    [request setHTTPBody:postData];
    
    return request;
}

-(void)connectionDidFinishLoading:(NSURLConnection*)connection
{
    NSError* error = NULL;
    NSDictionary* plist = [NSPropertyListSerialization propertyListWithData:self.receivedData
                                                                    options:nil
                                                                     format:nil
                                                                      error:&error];
    
    if (error != NULL)
    {
        NSLog(@"Fehler beim PList-Lesen: %@", error.description);
        NSLog(@"%@",[[NSString alloc] initWithData:self.receivedData
                                     encoding:NSUTF8StringEncoding]);
        [[NSNotificationCenter defaultCenter] postNotificationName:@"DidFailLoadingFromNetworkNotification"
                                                            object:self
                                                          userInfo:@{ @"loadedField" : self.field, @"error" : error }];
    } else {
        NSLog(@"Empfangen: %@", plist);
    }
    
    [self networkActivity:NO];
    
    [[NSNotificationCenter defaultCenter] postNotificationName:@"DidFinishLoadingFromNetworkNotification"
                                                        object:self
                                                      userInfo:@{ @"loadedField" : self.field, self.field : plist }];
}
@end
