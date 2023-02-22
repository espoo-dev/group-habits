import { ComponentType, ReactNode } from "react";
import { View } from "react-native";
import { classNames } from "../../../utils/strings";

interface IUIContainer {
  as?: ComponentType | keyof JSX.IntrinsicElements;
  children: ReactNode;
  className?: string;
  paddingNone?: boolean;
}

function UIContainer({
  as: Wrapper = View, children, className = '', paddingNone = false, ...rest
}: IUIContainer) {


  return <Wrapper
    className={classNames(
      "flex justify-between h-full w-full px-6 py-24",
      paddingNone ? "p-0" : "",
      className,
    )}
    {...rest}
  >
    {children}
  </Wrapper>
}

export { UIContainer };
