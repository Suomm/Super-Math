/*
 * Copyright 2015-2021 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.github.math.utils;

import java.io.*;

/**
 * <p>
 * 介绍信息
 *
 * @author 王帅
 * @since 1.0
 */
public class SerialUtils {

    private SerialUtils() {}

    /**
     * 创建一个新分配的byte数组。此数组包含当前对象内容， 并且缓冲区的有效内容已复制到该数组中。
     *
     * @return 以byte数组的形式返回当前对象内容。
     */
    public static byte[] toByteArray(Object obj) {
        ByteArrayOutputStream bao = new ByteArrayOutputStream(1024);
        write(obj, bao);
        return bao.toByteArray();
    }

    // 文件操作

    /**
     * 将当前类的的全部内容写入到指定的输出流参数中。
     *
     * @param stream 输出流
     * @return <code>true</code> 输出成功，<code>false</code> 输出失败。
     */
    public static boolean write(Object obj, OutputStream stream) {
        if (obj == null || stream == null) {
            return false;
        }
        try (ObjectOutputStream out = new ObjectOutputStream(stream)) {
            out.writeObject(obj);
            out.flush();
        } catch (IOException e) {
            return false;
        }
        return true;
    }

    /**
     * 将当前类的的全部内容输出到指定的文件中。
     *
     * @param path 输出文件的路径
     * @return <code>true</code> 输出成功，<code>false</code> 输出失败。
     */
    public static boolean write(Object obj, String path) {
        try (FileOutputStream fos = new FileOutputStream(path);
             BufferedOutputStream bos = new BufferedOutputStream(fos)) {
            return write(obj, bos);
        } catch (final IOException e) {
            return false;
        }
    }

}
