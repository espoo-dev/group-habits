import { ReactNode } from 'react';
import { GestureResponderEvent, Pressable, Text } from "react-native";
import { classNames } from '../../../utils/strings';

const VARIANTS = {
  primary: {
    pressable: 'border-transparent bg-indigo-600 hover:bg-indigo-700 focus:ring-indigo-500',
    text: 'text-white',
  },
  secondary: {
    pressable: 'border-transparent bg-indigo-100 hover:bg-indigo-200 focus:ring-indigo-500',
    text: 'text-indigo-700',
  },
  white: {
    pressable: 'border-gray-300 bg-white hover:bg-gray-50 focus:ring-indigo-500',
    text: 'text-gray-700',
  },
}

const SIZES = {
  'extra-small': 'rounded px-2.5 py-1.5 text-xs"',
  small: 'rounded-md px-3 py-2 text-sm leading-4"',
  medium: 'rounded-md px-4 py-2 text-sm"',
  large: 'rounded-md px-4 py-2 text-base"',
  'extra-large': 'rounded-md px-6 py-3 text-base"',
}

interface IUIButton {
  children: ReactNode;
  onPress: (event: GestureResponderEvent) => void;
  size?: keyof typeof SIZES;
  variant?: keyof typeof VARIANTS;
  weight?: string,
  icon?: string;
}


function UIButton({
  children, onPress, size = 'medium', variant = 'primary'
}: IUIButton) {
  return <Pressable
    className={classNames(
      "inline-flex items-center border shadow-sm focus:outline-none focus:ring-2 focus:ring-offset-2",
      SIZES[size],
      VARIANTS[variant].pressable
    )}
    onPress={onPress}
  >
    <>
      <Text
        className={classNames(
          "flex text-center w-full font-bold",
          VARIANTS[variant].text
        )}
      >
        {children}
      </Text>
    </>
  </Pressable>
}

export { UIButton };
