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
      "flex justify-between h-full px-6 py-8",
      className,
    )}
    {...rest}
  >
    {children}
  </Wrapper>
}

export { UIContainer };
