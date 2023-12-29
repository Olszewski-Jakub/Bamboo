package live.olszewski.bamboo.panda.connection.models;

/**
 * This enum represents the connection status of a Panda device.
 * It can be one of the following:
 * - CONNECTED: The device is currently connected.
 * - DISCONNECTED: The device is currently disconnected.
 * - UNKNOWN: The connection status of the device is unknown.
 */
public enum PandaStatus {
    CONNECTED,    // Represents a connected Panda device.
    DISCONNECTED, // Represents a disconnected Panda device.
    UNKNOWN       // Represents a Panda device with unknown connection status.
}