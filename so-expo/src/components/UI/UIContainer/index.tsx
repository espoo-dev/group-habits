import { ComponentType, ReactNode } from "react";
import { View } from "react-native";
import { classNames } from "../../../utils/strings";

interface IUIContainer {
  as?: ComponentType | keyof JSX.IntrinsicElements;
  children: ReactNode;
  className: string;
}

function UIContainer({
  as: Wrapper = View, children, className, ...rest
}: IUIContainer) {


  return <Wrapper
    className={classNames(
      "flex justify-between px-4 py-8 h-full",
      className,
    )}
    {...rest}
  >
    {children}
  </Wrapper>
}

export { UIContainer };
