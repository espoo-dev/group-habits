import { act, render, screen } from '@testing-library/svelte';
import '@testing-library/jest-dom';
import Toast from './Toast.svelte';
import { notifications } from '../../../../src/infra/notification/notification';

const sut = () => {
  render(Toast);
};

describe('Habit component', () => {
  beforeEach(() => {
    sut();
  });

  it.each(['default', 'success', 'danger', 'warning', 'info'])('should render %s notification', async (type) => {
    const message = `${type} notification msg`;
    await act(() => {
      notifications[type](message, type);
    })
    expect(screen.getByText(message)).toBeInTheDocument();
  });

  it('should render notification by sender', async () => {
    const message = `test by send`;
    await act(() => {
      notifications.send(message);
    })
    screen.debug()
    expect(screen.getByText(message)).toBeInTheDocument();
  });
});
