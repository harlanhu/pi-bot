package cn.tpkf.pi.devices.gpio.i2c;

import cn.tpkf.pi.devices.gpio.AbstractGpioDevice;
import cn.tpkf.pi.enums.BCMEnums;
import cn.tpkf.pi.manager.DeviceManager;
import com.pi4j.io.i2c.I2C;
import com.pi4j.io.i2c.I2CConfig;
import com.pi4j.io.i2c.I2CConfigBuilder;
import com.pi4j.plugin.linuxfs.provider.i2c.LinuxFsI2CProvider;
import lombok.Getter;

/**
 * I2C 设备
 *
 * @author Harlan
 * @email isharlan.hu@gmali.com
 * @date 2023/12/6
 */
public abstract class AbstractI2CDevice extends AbstractGpioDevice {

    protected final I2C i2C;

    @Getter
    protected final Integer bus;

    @Getter
    protected final Integer device;

    protected AbstractI2CDevice(DeviceManager deviceManager, String id, String name, Integer bus, Integer device, BCMEnums address) {
        super(deviceManager, id, name, address);
        this.bus = bus;
        this.device = device;
        i2C = deviceManager.execute(c -> {
            I2CConfig config = I2CConfigBuilder.newInstance(c)
                    .id(id)
                    .name(name)
                    .bus(bus)
                    .device(device)
                    .description(getDescription())
                    .provider(LinuxFsI2CProvider.class)
                    .build();
            return c.create(config);
        });
    }
}