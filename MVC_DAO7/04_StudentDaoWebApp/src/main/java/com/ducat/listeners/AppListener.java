package com.ducat.listeners;

import jakarta.servlet.AsyncEvent;
import jakarta.servlet.AsyncListener;
import jakarta.servlet.ServletContextAttributeEvent;
import jakarta.servlet.ServletContextAttributeListener;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.ServletRequestAttributeEvent;
import jakarta.servlet.ServletRequestAttributeListener;
import jakarta.servlet.ServletRequestEvent;
import jakarta.servlet.ServletRequestListener;
import jakarta.servlet.annotation.WebListener;
import jakarta.servlet.http.HttpSessionActivationListener;
import jakarta.servlet.http.HttpSessionAttributeListener;
import jakarta.servlet.http.HttpSessionBindingEvent;
import jakarta.servlet.http.HttpSessionBindingListener;
import jakarta.servlet.http.HttpSessionEvent;
import jakarta.servlet.http.HttpSessionIdListener;
import jakarta.servlet.http.HttpSessionListener;
import java.io.IOException;

@WebListener
public class AppListener implements ServletContextListener, ServletContextAttributeListener, HttpSessionListener,
        HttpSessionAttributeListener, HttpSessionActivationListener, HttpSessionBindingListener, HttpSessionIdListener,
        ServletRequestListener, ServletRequestAttributeListener, AsyncListener {

    // ---------------------- ServletContextListener ----------------------

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("🚀 Application started! Context initialized.");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("🛑 Application stopped! Context destroyed.");
    }

    // ---------------------- ServletContextAttributeListener ----------------------

    @Override
    public void attributeAdded(ServletContextAttributeEvent scae) {
        System.out.println("🆕 Context Attribute Added: " + scae.getName() + " = " + scae.getValue());
    }

    @Override
    public void attributeRemoved(ServletContextAttributeEvent scae) {
        System.out.println("❌ Context Attribute Removed: " + scae.getName());
    }

    @Override
    public void attributeReplaced(ServletContextAttributeEvent scae) {
        System.out.println("🔄 Context Attribute Replaced: " + scae.getName() + " = " + scae.getValue());
    }

    // ---------------------- HttpSessionListener ----------------------

    @Override
    public void sessionCreated(HttpSessionEvent se) {
        System.out.println("🟢 New Session Created: ID = " + se.getSession().getId());
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        System.out.println("🔴 Session Destroyed: ID = " + se.getSession().getId());
    }

    // ---------------------- HttpSessionIdListener ----------------------

    @Override
    public void sessionIdChanged(HttpSessionEvent se, String oldSessionId) {
        System.out.println("🔄 Session ID Changed: Old ID = " + oldSessionId + ", New ID = " + se.getSession().getId());
    }

    // ---------------------- HttpSessionAttributeListener ----------------------

    @Override
    public void attributeAdded(HttpSessionBindingEvent event) {
        System.out.println("🆕 Session Attribute Added: " + event.getName() + " = " + event.getValue());
    }

    @Override
    public void attributeRemoved(HttpSessionBindingEvent event) {
        System.out.println("❌ Session Attribute Removed: " + event.getName());
    }

    @Override
    public void attributeReplaced(HttpSessionBindingEvent event) {
        System.out.println("🔄 Session Attribute Replaced: " + event.getName() + " = " + event.getValue());
    }

    // ---------------------- HttpSessionActivationListener ----------------------

    @Override
    public void sessionWillPassivate(HttpSessionEvent se) {
        System.out.println("📦 Session Will Passivate: ID = " + se.getSession().getId());
    }

    @Override
    public void sessionDidActivate(HttpSessionEvent se) {
        System.out.println("📦 Session Did Activate: ID = " + se.getSession().getId());
    }

    // ---------------------- HttpSessionBindingListener ----------------------

    @Override
    public void valueBound(HttpSessionBindingEvent event) {
        System.out.println("🛠️ Object Bound to Session: " + event.getName());
    }

    @Override
    public void valueUnbound(HttpSessionBindingEvent event) {
        System.out.println("🛠️ Object Unbound from Session: " + event.getName());
    }

    // ---------------------- ServletRequestListener ----------------------

    @Override
    public void requestInitialized(ServletRequestEvent sre) {
        System.out.println("➡ Request Initialized from IP: " + sre.getServletRequest().getRemoteAddr());
    }

    @Override
    public void requestDestroyed(ServletRequestEvent sre) {
        System.out.println("⬅ Request Destroyed");
    }

    // ---------------------- ServletRequestAttributeListener ----------------------

    @Override
    public void attributeAdded(ServletRequestAttributeEvent srae) {
        System.out.println("🆕 Request Attribute Added: " + srae.getName() + " = " + srae.getValue());
    }

    @Override
    public void attributeRemoved(ServletRequestAttributeEvent srae) {
        System.out.println("❌ Request Attribute Removed: " + srae.getName());
    }

    @Override
    public void attributeReplaced(ServletRequestAttributeEvent srae) {
        System.out.println("🔄 Request Attribute Replaced: " + srae.getName() + " = " + srae.getValue());
    }

    // ---------------------- AsyncListener ----------------------

    @Override
    public void onComplete(AsyncEvent event) throws IOException {
        System.out.println("✅ Async Processing Complete");
    }

    @Override
    public void onError(AsyncEvent event) throws IOException {
        System.out.println("❌ Async Processing Error: " + event.getThrowable().getMessage());
    }

    @Override
    public void onStartAsync(AsyncEvent event) throws IOException {
        System.out.println("🚀 Async Processing Started");
    }

    @Override
    public void onTimeout(AsyncEvent event) throws IOException {
        System.out.println("⏳ Async Processing Timeout");
    }
}
