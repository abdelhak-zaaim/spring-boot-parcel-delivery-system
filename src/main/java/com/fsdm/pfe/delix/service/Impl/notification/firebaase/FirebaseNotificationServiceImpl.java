/*
 *
 *  * @project : DeliX
 *  * @created : 16/05/2024, 18:52
 *  * @modified : 16/05/2024, 18:52
 *  * @description : This file is part of the DeliX project.
 *  * @license : MIT License
 *
 */

package com.fsdm.pfe.delix.service.Impl.notification.firebaase;

import com.fsdm.pfe.delix.entity.User;
import com.fsdm.pfe.delix.exception.personalizedexceptions.FirebaseUserNotFoundException;
import com.fsdm.pfe.delix.model.NotificationRequest;
import com.fsdm.pfe.delix.service.Impl.notification.UserNotificationServiceImpl;
import com.google.firebase.messaging.*;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.concurrent.ExecutionException;

@Service
@Slf4j
public class FirebaseNotificationServiceImpl extends UserNotificationServiceImpl {
    @Override
    public void sendNotificationToUser(String title, String message, User user) {
        super.sendNotificationToUser(title, message, user);
        if (user.getFirebaseUser().getMessagingKey() != null) {
            NotificationRequest request = new NotificationRequest();
            request.setTitle(title);
            request.setBody(message);
            request.setToken(user.getFirebaseUser().getMessagingKey());
            try {
                sendMessageToToken(request);
            } catch (InterruptedException | ExecutionException e) {
                log.error("An error occurred while sending a message to a token. Device token: " + user.getFirebaseUser().getMessagingKey(), e);
            }
        } else {
            throw new FirebaseUserNotFoundException("User with id: " + user.getId() + " has no firebase messaging key");
        }

    }

    @Override
    public void sendNotificationToAll(String title, String message) {
        super.sendNotificationToAll(title, message);
    }

    public void sendMessageToToken(NotificationRequest request)
            throws InterruptedException, ExecutionException {
        Message message = getPreconfiguredMessageToToken(request);
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String jsonOutput = gson.toJson(message);
        String response = sendAndGetResponse(message);
        log.info("Sent message to token. Device token: " + request.getToken() + ", " + response + " msg " + jsonOutput);
    }

    private String sendAndGetResponse(Message message) throws InterruptedException, ExecutionException {
        return FirebaseMessaging.getInstance().sendAsync(message).get();
    }


    private AndroidConfig getAndroidConfig(String topic) {
        return AndroidConfig.builder()
                .setTtl(Duration.ofMinutes(2).toMillis()).setCollapseKey(topic)
                .setPriority(AndroidConfig.Priority.HIGH)
                .setNotification(AndroidNotification.builder()
                        .setTag(topic).build()).build();
    }

    private ApnsConfig getApnsConfig(String topic) {
        return ApnsConfig.builder()
                .setAps(Aps.builder().setCategory(topic).setThreadId(topic).build()).build();
    }

    private Message getPreconfiguredMessageToToken(NotificationRequest request) {
        return getPreconfiguredMessageBuilder(request).setToken(request.getToken())
                .build();
    }

    private Message.Builder getPreconfiguredMessageBuilder(NotificationRequest request) {
        AndroidConfig androidConfig = getAndroidConfig(request.getTopic());
        ApnsConfig apnsConfig = getApnsConfig(request.getTopic());
        Notification notification = Notification.builder()
                .setTitle(request.getTitle())
                .setBody(request.getBody())
                .build();
        return Message.builder()
                .setApnsConfig(apnsConfig).setAndroidConfig(androidConfig).setNotification(notification);
    }
}