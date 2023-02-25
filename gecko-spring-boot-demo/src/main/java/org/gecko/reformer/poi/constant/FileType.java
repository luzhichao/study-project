package org.gecko.reformer.poi.constant;

import cn.hutool.core.util.StrUtil;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

/**
 * 文件类型后缀与response返回格式对照
 *
 * @author LZC
 * @version 1.1.2
 * @date 2022-11-10
 **/
@Getter
@AllArgsConstructor
public enum FileType {
    ALL(".*", "application/octet-stream"),
    XLS(".xls", "application/vnd.ms-excel"),
    XLSX(".xlsx", "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"),
    PPT(".ppt", "application/vnd.ms-powerpoint"),
    PPTX(".pptx", "application/vnd.openxmlformats-officedocument.presentationml.presentation"),
    DOC(".doc", "application/msword"),
    DOCX(".docx", "application/vnd.openxmlformats-officedocument.wordprocessingml.document"),
    ZIP(".zip", "application/x-zip-compressed"),
    RAR(".rar", "application/x-zip-compressed"),
    WMV(".wmv", "video/x-ms-wmv"),
    MP3(".mp3", "audio/mpeg"),
    MP4(".mp4", "video/mp4"),
    GIF(".gif", "image/gif"),
    JPG(".jpg", "image/jpeg"),
    PNG(".png", "image/png"),
    BMP(".bmp", "image/bmp"),
    PSD(".psd", "application/octet-stream"),
    ICO(".ico", "image/x-icon"),
    Z_7(".7z", "application/octet-stream"),
    EXE(".exe", "application/octet-stream"),
    AVI(".avi", "video/avi"),
    RMVB(".rmvb", "application/vnd.rn-realmedia-vbr"),
    GP_3(".3gp", "application/octet-stream"),
    FLV(".flv", "application/octet-stream"),
    WAV(".wav", "audio/wav"),
    KRC(".krc", "application/octet-stream"),
    LRC(".lrc", "application/octet-stream"),
    TXT(".txt", "text/plain"),
    PDF(".pdf", "application/pdf"),
    CHM(".chm", "application/octet-stream"),
    MDB(".mdb", "application/msaccess"),
    SQL(".sql", "application/octet-stream"),
    CON(".con", "application/octet-stream"),
    LOG(".log", "text/plain"),
    DAT(".dat", "application/octet-stream"),
    INI(".ini", "application/octet-stream"),
    PHP(".php", "application/octet-stream"),
    HTMl(".html", "text/html"),
    TTF(".ttf", "application/octet-stream"),
    FON(".fon", "application/octet-stream"),
    JS(".js", "application/x-javascript"),
    XML(".xml", "text/xml"),
    DLL(".dll", "application/octet-stream"),
    ;

    private final String suffix;
    private final String type;

    /**
     * 根据文件后缀获取Content-Type
     *
     * @param suffix
     * @return java.lang.String
     * @throws
     * @author LZC
     * @date 2022-11-10
     * @version 1.1.2
     **/
    public static String getTypeBySuffix(String suffix) {
        final FileType type = Arrays.stream(FileType.values())
                .filter(f -> StrUtil.equalsIgnoreCase(suffix, f.getSuffix()))
                .findFirst().orElse(FileType.ALL);
        return type.getType();
    }
}
