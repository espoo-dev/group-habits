import { ReactNode } from 'react';
import { GestureResponderEvent, Pressable, Text } from "react-native";

interface IUIButton {
  children: ReactNode;
  onPress: (event: GestureResponderEvent) => void;
  size?: string;
  weight?: string,
}

function UIButton({ children, onPress }: IUIButton) {
  return <Pressable
    onPress={onPress}
    className="flex-1 items-center justify-center bg-red-400 text-yellow-200"
  >

    <Text className="text-yellow-300">
      {children}
    </Text>
  </Pressable>
}

export { UIButton };
