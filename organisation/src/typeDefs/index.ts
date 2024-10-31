import { gql } from "@elysiajs/apollo";

const typeDefs = gql`
  type AuthenticationLayerSettings {
    organizationId: Boolean
    brandId: Boolean
    locationId: Boolean
    channelId: Boolean
  }

  type Query {
    getAuthenticationLayerSettingsByVitals(
      organizationId: ID!
      brandId: ID!
      locationId: ID!
      channelId: ID!
    ): AuthenticationLayerSettings
  }
`;

export default typeDefs;
