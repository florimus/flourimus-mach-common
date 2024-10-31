import service from "../services";

const resolvers = {
  Query: {
    getAuthenticationLayerSettingsByVitals: (
      _: unknown,
      args: AuthenticationLayerSettingsByVitalsRequest,
      ctx: any
    ) => service.getAuthenticationLayerSettingsByVitals(args),
  },
};

export default resolvers;
