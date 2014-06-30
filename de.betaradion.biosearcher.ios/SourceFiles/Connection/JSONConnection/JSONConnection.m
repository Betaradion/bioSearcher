//
//  JSONConnection.m
//  bioSearcher
//
//  Created by Martin on 26.06.14.
//  Copyright (c) 2014 Betaradion. All rights reserved.
//

#import "JSONConnection.h"

@implementation JSONConnection{
    DataType loadedDataType;
}


-(void)loadData:(DataType)type forParentId:(NSNumber *)parentID
{
    loadedDataType = type;
    NSMutableString *path = [NSMutableString stringWithString:webPath];
    
    switch (loadedDataType) {
        case DataTypeFamilies:
            [path appendString:familiesPath];
            break;
        case DataTypeFamily:
            [path appendString:familiesPath];
            [path appendString:[NSString stringWithFormat:@"%i/",parentID.intValue]];
            break;
        case DataTypeCharacters:
            [path appendString:familiesPath];
            [path appendString:[NSString stringWithFormat:@"%i/",parentID.intValue]];
            [path appendString:charactersPath];
            break;
        case DataTypeOptions:
            [path appendString:familiesPath];
            [path appendString:[NSString stringWithFormat:@"%i/",parentID.intValue]];
            [path appendString:charactersPath];
            [path appendString:[NSString stringWithFormat:@"%i/",parentID.intValue]];
            [path appendString:optionsPath];
            break;
        case DataTypeProfile:
            break;
            
        default:
            assert(@"No valid request");
            break;
    }
    
    NSURLRequest *request = [NSURLRequest requestWithURL:[NSURL URLWithString:path]];

    NSURLConnection *conn = [[NSURLConnection alloc] initWithRequest:request delegate:self];
    [conn start];
}

-(void)searchForSpeciesWithFamilyID:(NSNumber *)id andCharacters:(NSDictionary *)characters
{
    NSMutableString *path = [NSMutableString stringWithString:webPath];
    [path appendString:search];
    [path appendString:[NSString stringWithFormat:@"?family=%@", id.stringValue]];

    for (NSString *key in characters) {
        NSString *parameter = [NSString stringWithFormat:@"&%@=%@", key, characters[key]];
        [path appendString:parameter];
    }

    NSURLRequest *request = [NSURLRequest requestWithURL:[NSURL URLWithString:path]];
    
    NSURLConnection *conn = [[NSURLConnection alloc] initWithRequest:request delegate:self];
    [conn start];

}

-(void)connectionDidFinishLoading:(NSURLConnection*)connection
{
    NSError *error = nil;
    id object = [NSJSONSerialization
                 JSONObjectWithData:self.receivedData
                 options:0
                 error:&error];
    NSLog([[NSString alloc] initWithData:self.receivedData encoding:NSUTF8StringEncoding]);
    
    if ([NSJSONSerialization isValidJSONObject:object])
    {
        NSLog(@"Completed!");
        
        
        if([object isKindOfClass:[NSArray class]])
        {
            NSArray *results = object;
            
            NSLog(@"%@", results);
            
            [[NSNotificationCenter defaultCenter] postNotificationName:@"DidFinishLoadingFromNetworkNotification"
                                                                object:self
                                                              userInfo:@{ @"loadedField" : [NSString stringWithFormat:@"%u",loadedDataType],
                                                                          @"data" : results}];
        } else  if ([object isKindOfClass:[NSDictionary class]]){
            NSDictionary *results = object;
            NSLog(@"%@", results);
            [[NSNotificationCenter defaultCenter] postNotificationName:@"DidFinishLoadingFromNetworkNotification"
                                                                object:self
                                                              userInfo:@{ @"loadedField" : [NSString stringWithFormat:@"%u",loadedDataType],
                                                                          @"data" : results}];
        }
    } else {
        NSLog(@"Error no valid JSON-Object");
    }
    
    [self networkActivity:NO];
    
}
<<<<<<< HEAD

=======
>>>>>>> branch 'master' of https://github.com/Betaradion/bioSearcher.git
@end
