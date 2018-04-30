package gov.uscourts.ao.mobileBriefcase.common;

public interface iOSCapabilities {

	/**
	 * Which mobile OS platform to use.
	 */
	String PLATFORM_NAME = "platformName";

	/**
	 * Mobile OS version.
	 */
	String PLATFORM_VERSION = "platformVersion";

	/**
	 * Unique device identifier of the connected physical device.
	 */
	String UDID = "udid";

	/**
	 * The kind of mobile device or emulator/simulator to use.
	 */
	String DEVICE_NAME = "deviceName";

	/**
	 * Bundle ID of the app under test.
	 */
	String BUNDLE_ID = "bundleId";

	/**
	 * provisioning profile that is installed on the device
	 */
	String XCODE_ORG_ID = "xcodeOrgId";

	/**
	 * iPhone Developer
	 */
	String XCODE_SIGNING_ID = "xcodeSigningId";

	/**
	 * Handling Allerts
	 */
	String AUTO_ACCEPT_ALERTS = "autoAcceptAlerts";

	/**
	 * allowing screenshots
	 */
	String TAKES_SCREENSHOT = "takes_screehshot";

	/** host number of the device */
	String HOST = "host";

	/**
	 * The absolute local path or remote http URL
	 */
	String APP = "app";

}
