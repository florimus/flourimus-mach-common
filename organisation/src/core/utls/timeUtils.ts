import moment from "moment";


const LOCAL_DATE_TIME_FORMAT = "YYYY-MM-DD HH:mm:ss"

/**
 * Returns the current time in the format of LOCAL_DATE_TIME_FORMAT
 * @returns {String} a string representing the current time
 */
export const getCurrentTime = () => {
  const now = moment();
  return now.format(LOCAL_DATE_TIME_FORMAT);
}