package edu.cmu.ri.createlab.terk.robot.finch.commands;

import edu.cmu.ri.createlab.usb.hid.HIDCommandResult;
import edu.cmu.ri.createlab.util.ByteUtils;

/**
 * @author Chris Bartley (bartley@cmu.edu)
 */
public final class GetObstacleSensorCommandStrategy extends ReturnValueCommandStrategy<boolean[]>
   {
   /** The command character used to request the value of the finch's obstacle sensors. */
   private static final byte[] COMMAND = {'I'};

   /** The size of the expected response, in bytes */
   private static final int SIZE_IN_BYTES_OF_EXPECTED_RESPONSE = 2;

   protected int getSizeOfExpectedResponse()
      {
      return SIZE_IN_BYTES_OF_EXPECTED_RESPONSE;
      }

   protected byte[] getCommand()
      {
      return COMMAND.clone();
      }

   public boolean[] convertResult(final HIDCommandResult result)
      {
      if (result != null && result.wasSuccessful())
         {
         final byte[] responseData = result.getData();
         return new boolean[]{ByteUtils.unsignedByteToInt(responseData[0]) == 1,
                              ByteUtils.unsignedByteToInt(responseData[1]) == 1};
         }

      return null;
      }
   }