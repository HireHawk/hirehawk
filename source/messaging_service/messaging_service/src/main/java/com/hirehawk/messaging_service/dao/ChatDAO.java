package com.hirehawk.messaging_service.dao;

import com.hirehawk.messaging_service.entity.Chat;
import com.hirehawk.messaging_service.entity.ChatMediaFile;

import java.util.Date;

public interface ChatDAO {

    public void addChat(String name, int icon, String additionalInfo);

    public void addMediaFile(String filename, String extention, byte[] media, String mimetype, byte[] thumbnail);

    public void addMessage(int author, int receiver, int chat, String text, int mediaFile, Date stamp, Date editStamp, int deleted);

    public Chat getChatById(int id);

    public ChatMediaFile getMediaFileById(int id);
}
