import { writable, derived, type Readable } from "svelte/store"

export interface NotifacationProps {
  id: string;
  message: string
  type: string
  timeout: number
}

const createNotificationStore = () => {
  const _notifications = writable<NotifacationProps[]>([])

  const send = (message: string, type = "default", timeout: number) => {
    _notifications.update(state => {
      return [...state, { id: id(), type, message, timeout }]
    })
  }

  const notifications = derived(_notifications, ($_notifications: NotifacationProps[], set) => {
    set($_notifications)
    if ($_notifications.length > 0) {
      const timer = setTimeout(() => {
        _notifications.update(state => {
          state.shift()
          return state
        })
      }, $_notifications[0].timeout)
      return () => {
        clearTimeout(timer)
      }
    }
  })
  const { subscribe } = notifications as Readable<NotifacationProps[]>

  return {
    subscribe,
    send,
    default: (msg: string, timeout: number) => send(msg, "default", timeout),
    danger: (msg: string, timeout: number) => send(msg, "danger", timeout),
    warning: (msg: string, timeout: number) => send(msg, "warning", timeout),
    info: (msg: string, timeout: number) => send(msg, "info", timeout),
    success: (msg: string, timeout: number) => send(msg, "success", timeout),
  }
}

function id() {
  return '_' + Math.random().toString(36).substr(2, 9);
};

export const notifications = createNotificationStore()