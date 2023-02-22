import { ReactNode } from "react";
import { ActivityIndicator, Pressable, PressableProps, Text, View } from "react-native";
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
  small: 'rounded-md px-3 py-2.5 text-sm leading-4"',
  medium: 'rounded-md px-4 py-2.5 text-sm"',
  large: 'rounded-md px-4 py-2.5 text-base"',
  'extra-large': 'rounded-md px-6 py-3 text-base"',
}

interface IUIButton extends PressableProps {
  className?: string;
  children: ReactNode;
  loading?: boolean;
  size?: keyof typeof SIZES;
  variant?: keyof typeof VARIANTS;
  weight?: string,
  icon?: string;
}


function UIButton({
  children, className = "", loading, onPress, size = 'medium', variant = 'primary', ...rest
}: IUIButton) {
  return <Pressable
    className={classNames(
      "inline-flex items-center justify-center border shadow-sm focus:outline-none focus:ring-2 focus:ring-offset-2",
      SIZES[size],
      VARIANTS[variant].pressable,
      className,
    )}
    onPress={onPress}
    {...rest}
  >
    <Text
      className={classNames(
        "flex items-center justify-center text-center w-full font-bold",
        VARIANTS[variant].text
      )}
    >
      {loading ? <View className="min-w-full flex items-center justify-center">
        <ActivityIndicator color="#5C93FA" />
      </View>
        : children}
    </Text>
  </Pressable>
}

export { UIButton };
