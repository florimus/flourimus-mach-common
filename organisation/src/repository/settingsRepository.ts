import System from "../schemas/SystemSchema";

const getSettingsByNameAndCode = async (
  vitals: AuthenticationLayerSettingsByVitalsRequest
) => {
  return await System.findOne({
    ...vitals, 
    isActive: true
  });
};

export default {
  getSettingsByNameAndCode,
};
