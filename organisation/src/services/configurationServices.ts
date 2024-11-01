import NotFoundError from "../core/exceptions/NotFoundError";
import { Logger } from "../core/logger";
import settingsRepository from "../repository/settingsRepository";

export const getAuthenticationLayerSettingsByVitals = async (
  vitals: AuthenticationLayerSettingsByVitalsRequest
) => {
  Logger.info(
    "224d92e2-496d-4a07-af09-7666a9629d90",
    `Received request for getAuthenticationLayerSettingsByVitals: ${JSON.stringify(
      vitals
    )}`
  );
  const settings = await settingsRepository.getSettingsByNameAndCode(vitals);
  Logger.info(
    "03ef32ef-589d-4314-8e31-26128bcf4f46",
    `Found authentication layer configurations: ${JSON.stringify(
      settings?.defaultConfigurations
    )}`
  );
  if (!settings) {
    throw new NotFoundError("settings not found");
  }
  return settings?.defaultConfigurations;
};
