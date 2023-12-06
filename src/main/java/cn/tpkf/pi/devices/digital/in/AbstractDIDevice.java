package cn.tpkf.pi.devices.digital.in;

import cn.tpkf.pi.devices.digital.AbstractDigitalDevice;
import cn.tpkf.pi.enums.BCMEnums;
import cn.tpkf.pi.manager.DeviceManager;
import com.pi4j.io.gpio.digital.DigitalInput;
import com.pi4j.io.gpio.digital.DigitalInputConfig;
import com.pi4j.io.gpio.digital.DigitalInputConfigBuilder;

/**
 * @author Harlan
 * @email isharlan.hu@gmali.com
 * @date 2023/12/6
 */
public abstract class AbstractDIDevice extends AbstractDigitalDevice {

    protected final DigitalInput digitalInput;

    protected AbstractDIDevice(DeviceManager deviceManager, String id, String name, BCMEnums address) {
        super(deviceManager, id, name, address);
        digitalInput = deviceManager.execute(c -> {
            DigitalInputConfig config = DigitalInputConfigBuilder.newInstance(c)
                    .id(id)
                    .name(name)
                    .address(address.getVale())
                    .description(getDescription())
                    .build();
            return c.create(config);
        });
    }
}