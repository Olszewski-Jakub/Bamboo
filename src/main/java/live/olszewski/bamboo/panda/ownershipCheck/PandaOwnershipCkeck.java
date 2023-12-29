package live.olszewski.bamboo.panda.ownershipCheck;

/**
 * This interface defines a method for checking if a user is the owner of a Panda device.
 */
public interface PandaOwnershipCkeck {

    /**
     * Checks if the user is the owner of a Panda device.
     *
     * @param pandaId The ID of the Panda device.
     * @return True if the user is the owner of the Panda device, false otherwise.
     */
    boolean isPandaOwner(Long pandaId);
}